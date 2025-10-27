import { Link } from "react-router";
import Navigation from "../../components/Navigation";
import claimListData_01 from "./ex_claims_list_pg01.json";
// import claimListData_02 from './ex_claims_list_pg02.json';

function ListOfClaims() {
    const tempData = claimListData_01;

    const dateConverter = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString("en-US", {
            year: "numeric",
            month: "2-digit",
            day: "2-digit",
        });
    };

    const claimNumberSanitizer = (claimNum) => {
        return claimNum.substring(1);
    };

    const pageCounter = (numOfPages) => {
        const numericLinks = [];
        for (let i = 0; i < numOfPages; i++) {
            if (i == numOfPages) {
                numericLinks.push(<Link key={i} to="" >{ i + 1 }</Link>);
            } else {
                numericLinks.push(<Link key={i} to="" >{ i + 1 }</Link>);
            }
            
        }
        return numericLinks;
    };

    // to={`/claims/${ claimNumberSanitizer(claim.claimNumber) }`}

    // const search = (formData) => {
    //     const statusCheckboxes = document.querySelectorAll(`input[name="status"]:checked`);
    //     const statusList = [];
    //     statusCheckboxes.forEach((checkbox) => {
    //         statusList.push(checkbox.value);
    //     });

    // };

    return (
        <list-of-claims>
            <Navigation />
            <filter-menu>
                <form >
                    <fieldset className="fieldset-status">
                        <legend>Select the Status</legend>
                        {/* DEFAULT: Processed */}
                        <user-input-field>
                            <input
                                type="checkbox"
                                name="status"
                                id="processed"
                                value="PROCESSED"
                                defaultChecked
                            />
                            <label htmlFor="processed">Processed</label>
                        </user-input-field>
                        <user-input-field>
                            <input
                                type="checkbox"
                                name="status"
                                id="paid"
                                value="PAID"
                            />
                            <label htmlFor="paid">Paid</label>
                        </user-input-field>
                        <user-input-field>
                            <input
                                type="checkbox"
                                name="status"
                                id="in-review"
                                value="IN_REVIEW"
                            />
                            <label htmlFor="in-review">In Review</label>
                        </user-input-field>
                        <user-input-field>
                            <input
                                type="checkbox"
                                name="status"
                                id="denied"
                                value="DENIED"
                            />
                            <label htmlFor="denied">Denied</label>
                        </user-input-field>
                        <user-input-field>
                            <input
                                type="checkbox"
                                name="status"
                                id="submitted"
                                value="SUBMITTED"
                            />
                            <label htmlFor="submitted">Submitted</label>
                        </user-input-field>
                    </fieldset>

                    <fieldset className="fieldset-dates">
                        <legend>Select the Date-Range</legend>
                        {/* DEFAULT: Start - Null/All */}
                        <user-input-field>
                            <input
                                type="date"
                                name="serviceStartDate"
                                id="service-start-date"
                            />
                            <label htmlFor="service-start-date">
                                Start Date
                            </label>
                        </user-input-field>

                        {/* DEFAULT: End - Today */}
                        <user-input-field>
                            <input
                                type="date"
                                name="serviceEndDate"
                                id="service-end-date"
                            />
                            <label htmlFor="service-end-date">End Date</label>
                        </user-input-field>
                    </fieldset>

                    <fieldset className="fieldset-text">
                        <legend>Type Provider / Claim Number</legend>
                        {/* DEFAULT: Null/All */}
                        <user-input-field>
                            <input
                                type="text"
                                name="name"
                                id="name"
                                placeholder="River Medical"
                            />
                            <label htmlFor="name">Provider</label>
                        </user-input-field>

                        {/* DEFAULT: Null/All */}
                        <user-input-field>
                            <input
                                type="text"
                                name="claimNumber"
                                id="claim-number"
                                placeholder="#C-10310"
                            />
                            <label htmlFor="claim-number">Claim Number</label>
                        </user-input-field>
                    </fieldset>
                    <button className="btn-submit" type="submit">Search</button>
                </form>
            </filter-menu>

            <paginated-claims>
                {tempData.content.map((claim) => {
                    return (
                        <Link
                            key={claim.claimNumber}
                            to={`/claims/${ claimNumberSanitizer(claim.claimNumber) }`}
                        >
                            <claim-el>
                                <span style={{ width: '85px' }}>{claim.claimNumber}</span>
                                <span style={{ width: '225px' }}>
                                    {dateConverter(claim.serviceStartDate)}-
                                    {dateConverter(claim.serviceEndDate)}
                                </span>
                                <span style={{ width: '225px' }}>{claim.name}</span>
                                <span style={{ width: '105px' }}>{claim.status}</span>
                                <span style={{ width: '105px' }}>
                                    $
                                    {claim.totalMemberResponsibility.toLocaleString(
                                        "en-US"
                                    )}
                                </span>
                                <span>View &#8614;</span>
                            </claim-el>
                        </Link>
                    )

                })}
                <claim-el ></claim-el>
            </paginated-claims>

            <lower-navigation>
                <span>Page { tempData.page.number + 1} of { tempData.page.totalPages }</span>
                <span>
                    <select name="pageSize" id="pageSize" disabled={ tempData.page.totalElements <= 10 ? true : false }>
                        <option value="10" selected>10</option>
                        <option value="25">25</option>
                    </select>
                    <span> per page</span>
                </span>

                {/* DESC: Conditionally render the "Prev" and "Next" functionality */}
                { tempData.page.totalPages != 1 &&
                    <>
                        <span>&lt; Prev</span>
                            { pageCounter(tempData.page.totalPages) }
                        <span>Next &gt;</span>
                    </>
                }
            </lower-navigation>
        </list-of-claims>
    );
}

export default ListOfClaims;